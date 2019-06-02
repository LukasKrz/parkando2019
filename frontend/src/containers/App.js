import React, { Component } from 'react';
import { Router, Route, Switch, matchPath} from 'react-router-dom';
import moment from 'moment';

import history from '../modules/history';

// import users from '../mocks/users.js';

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
    user_type: 'Dzienne',
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
    });
  }

  logUser = (cardNumber, userName, userSurname, userType) => {
    this.setState({
      card_id: cardNumber,
      user_type: userType,
      userName: userName,
      userSurname: userSurname
    })
  }

  // componentWillMount() {
  //   // TODO remove when backend is in
  //   let daily = [];
  //   let weekends = [];


componentDidMount() {
  const type = this.state.user_type.toLowerCase();
  fetch(`miejsca/dostepnepodstawowe/${type}`)
  .then(response => response.json())
    .then(data => {
      console.log('TYP: ', type);
      console.log('LISTA: ', data);
      let occupiedSpaces = [];
      data.map(a => occupiedSpaces.push(a.parkPlaceId))
      console.log('WYBRANE: ', occupiedSpaces);
      
      type === 'dzienne'
      ? 
      this.setState({occupiedSpacesForDaily: occupiedSpaces})
      : this.setState({occupiedSpacesForWeekends: occupiedSpaces})
    })
}

// hello = () => {
// fetch('/api/hello')
//  .then(response => response.text())
//  .then(message => {
//     this.setState({message: message});
//   });
// };

  render() {
    // console.log('TEsT: ', this.state.test);
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
                  // users={users.parkandoUsers}
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
                    this.state.user_type === 'Dzienne'
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
                    this.state.user_type === 'Dzienne'
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
