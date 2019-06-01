import React, { Component } from 'react';
import { Router, Route, Switch, matchPath} from 'react-router-dom';
import moment from 'moment';

import history from '../modules/history';

import users from '../mocks/users.js';

import LoginPage from '../components/LoginPage/LoginPage';
import WelcomePage from '../components/WelcomePage/WelcomePage';
import ChoicePage from '../components/ParkingChoicePage/ChoicePage';
import ParkingChoicePage from '../components/ParkingChoicePage/ParkingChoicePage';
import ConfirmationPage from '../components/ConfirmationPage/ConfirmationPage';
import FinalConfirmationPage from '../components/ConfirmationPage/FinalConfirmationPage';

class App extends Component {
  state = {
    card_id: null,
    park_place_id: null,
    user_type: 'Dzienny',
    expiration_date: moment().add(6, 'd').format('DD.MM.YYYY'),
    userName: '',
    userSurname: '',
    occupiedSpacesForDaily: [],
    occupiedSpacesForWeekends: [],
    // TODO remove when backend is in
    test: ''
  }

  choiceHandler = (number) => {
    this.setState({
      park_place_id: number
    })
  }

  logUser = (cardNumber, userName, userSurname, userType) => {
    this.setState({
      card_id: cardNumber,
      user_type: userType,
      userName: userName,
      userSurname: userSurname
    })
  }

  componentWillMount() {
    // TODO remove when backend is in
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
    fetch('/api/hello')
      .then(response => response.text()) // res.json()
      .then(message => {
          this.setState({test: message});
    });
  }

// componentDidMount() {
//     setInterval(this.hello, 250);
// }

// hello = () => {
// fetch('/api/hello')
//  .then(response => response.text())
//  .then(message => {
//     this.setState({message: message});
//   });
// };

  render() {
    console.log('TEsT: ', this.state.test);
    console.log('user type', this.state.user_type);

    const userType = this.state.user_type;
    
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
                    userType === 'Dzienny'
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
                    userType === 'Dzienny'
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
                expirationDate={this.state.expiration_date}
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
