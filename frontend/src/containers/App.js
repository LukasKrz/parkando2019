import React, { Component } from 'react';
import { Router, Route, Switch, matchPath} from 'react-router-dom';
import moment from 'moment';

import history from '../modules/history';

import LoginPage from '../components/LoginPage/LoginPage';
import WelcomePage from '../components/WelcomePage/WelcomePage';
import ChoicePage from '../components/ParkingChoicePage/ChoicePage';
import ParkingChoicePage from '../components/ParkingChoicePage/ParkingChoicePage';
import ConfirmationPage from '../components/ConfirmationPage/ConfirmationPage';
import FinalConfirmationPage from '../components/ConfirmationPage/FinalConfirmationPage';
import FinalConfirmationWithTwoReservation from '../components/ConfirmationPage/FinalConfirmationWithTwoReservation';
import DayChoicePage from '../components/DayChoicePage/DayChoicePage';

class App extends Component {
  state = {
    card_id: null,
    park_place_id: null,
    user_type: '',
    expiration_date: moment().add(6, 'd').format('DD.MM.YYYY'),
    userName: '',
    userSurname: '',
    reservationDatePodstawowe: moment().add(6, 'd').format('DD.MM.YYYY'), 
    parkPlaceIdDodatkowe: null,
    dayOfWeek: 0, 
    reservationDateDodatkowe: moment().add(6, 'd').format('DD.MM.YYYY')
 }

  choiceHandler = (number) => {
    this.setState({
      park_place_id: number
    });
  }

  logUser = (cardNumber, userName, userSurname, userType, placeId, reservationDatePodstawowe, parkPlaceIdDodatkowe, dayOfWeek, reservationDateDodatkowe) => {
    this.setState({
      card_id: cardNumber,
      user_type: userType,
      userName: userName,
      userSurname: userSurname,
      park_place_id: placeId,
      reservationDatePodstawowe: reservationDatePodstawowe, 
      parkPlaceIdDodatkowe: parkPlaceIdDodatkowe,
      dayOfWeek: dayOfWeek, 
      reservationDateDodatkowe: reservationDateDodatkowe
    })
  }

  render() {
    console.log('APP: ', this.state.dayOfWeek);
    
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
                />
            }
            exact 
          />
          <Route
            path="/welcome/:card_id" 
            render={
              (props) =>
                <WelcomePage
                  {...props}
                  userName={this.state.userName}
                  userType={this.state.user_type}
                  dayNum={this.state.dayOfWeek}
                />
            }
          />
          <Route
            path='/choicePaking/:card_id/:extra_place'
            render={
              (props) =>
                <ParkingChoicePage
                  {...props}
                  choiceHandler={this.choiceParkingHandler}
                  userType={this.state.user_type}
                />}
          />
          <Route
            path='/choice/:card_id/:extra_place'
            render={
              (props) =>
                <ChoicePage
                  {...props}
                  choiceHandler={this.choiceHandler}
                  userType={this.state.user_type}
                  cardId={this.state.card_id}
                  emptySpaces={this.emptySpaces}
                />
            }
          />
          <Route
            path="/confirmation/:card_id/:extra_place/:park_place_id"
            render={
              (props) =>
                <ConfirmationPage
                  {...props}
                  choiceHandler={this.choiceHandler}
                  userType={this.state.user_type}
                  cardId={this.state.card_id}
                  userName={this.state.userName}
                  userSurname={this.state.userSurname}
                />
            }
            match={matchPath}
          />
          <Route
            path="/final-confirmation/:card_id/:extra_place/:park_place_id"
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
            <Route
            path="/day-choice/:card_id"
            render={
              (props) =>
              <DayChoicePage
                {...props}
                userType={this.state.user_type}
              />
            }
            match={matchPath}
          />
          <Route
            path="/two-reservation/:card_id/:extra_place/:park_place_id"
            render={
              (props) =>
              <FinalConfirmationWithTwoReservation
                {...props}
                userName={this.state.userName}
                userSurname={this.state.userSurname}
                userType={this.state.user_type}
                expirationDate={this.state.expiration_date}
                placeId={this.state.park_place_id}
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
