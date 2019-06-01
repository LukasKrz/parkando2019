import React, { Component } from 'react';
import { Router, Route, Switch, matchPath} from 'react-router-dom';

import history from './history';

import users from '../mocks/users.js';

import LoginPage from '../components/LoginPage/LoginPage';
import WelcomePage from '../components/WelcomePage/WelcomePage';
import ChoicePage from '../components/ParkingSpaceChoicePage/ChoicePage';
import ParkingChoicePage from '../components/ParkingChoicePage/ParkingChoicePage';
import ConfirmationPage from '../components/ConfirmationPage/ConfirmationPage';
import FinalConfirmationPage from '../components/ConfirmationPage/FinalConfirmationPage';

class App extends Component {
  state = {
    card_id: null,
    park_place_id: null, // TODO expiration date
    user_type: 'Dzienny',
    userName: '',
    userSurname: '',
    occupiedSpacesForDaily: [],
    occupiedSpacesForWeekends: [],
    test: []
  }

  choiceHandler = (number) => {
    this.setState({
      park_place_id: number
    })
  }

  logUser = (cardNumber, userName, userSurname, userType) => {
    this.setState({
      card_id: cardNumber,
      userName: userName,
      userSurname: userSurname,
      user_type: userType
    })
  }

  componentWillMount() {
    let daily = [];
    let weekends = [];

    users.parkandoUsers.map(a => {
      if(a.park_place_id !== null) {
        a.user_type === "Dzienny"
          ? daily.push(a.park_place_id)
          : weekends.push(a.park_place_id)
      }
    });

    this.setState({
      occupiedSpacesForDaily: daily,
      occupiedSpacesForWeekends: weekends,
    });
  }

  componentDidMount() {
    fetch("https://randomuser.me/api/?format=json&results=5")
      .then(res => res.json())
      .then(json => this.setState({ test: json.results }));
  }

  render() {
    this.state.test.map(e => console.log(e));      
    return (
      <Router history={history} choiceHandler={this.choiceHandler} >
      <main>
        <Switch>
          <Route
            path="/"
            render={
              (props) => 
                <LoginPage
                  {...props}
                  logUser={this.logUser} 
                  match={matchPath}
                  users={users.parkandoUsers}
                />
            }
            exact 
          />
          <Route
            path="/welcome/:card_id" 
            render={(props) => <WelcomePage {...props} userName={this.state.userName} />}
          />
          <Route
            path='/choicePaking/:card_id'
            render={
              (props) =>
                <ParkingChoicePage
                  {...props}
                  choiceHandler={this.choiceParkingHandler}
                  occupiedSpaces={
                    this.state.user_type === 'Dzienny'
                    ? this.state.occupiedSpacesForDaily
                    : this.state.occupiedSpacesForWeekends
                  }
                />}
          />
          <Route
            path='/choice/:card_id'
            render={
              (props) =>
                <ChoicePage
                  {...props}
                  choiceHandler={this.choiceHandler}
                  occupiedSpaces={
                    this.state.user_type === 'Dzienny'
                    ? this.state.occupiedSpacesForDaily
                    : this.state.occupiedSpacesForWeekends
                  }
                />
            }
          />
          <Route
            path="/confirmation/:card_id/:park_place_id"
            render={(props) => <ConfirmationPage {...props} userName={this.state.userName} userSurname={this.state.userSurname} />}
            match={matchPath}
          />
          <Route
            path="/final-confirmation/:card_id/:park_place_id"
            render={
              (props) =>
              <FinalConfirmationPage
                {...props}
                userName={this.state.userName}
                userSurname={this.state.userSurname}
                userType={this.state.user_type}
              />
            }
            match={matchPath}
          />
        </Switch>
      </main>
    </Router>
    );
  }
}

export default App;
