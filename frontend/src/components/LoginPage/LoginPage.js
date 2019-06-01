import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import bigLogo from '../../images/parkando.png';

class LoginPage extends Component {
  state = {
    userName: '',
    userSurname: '',
    card_id: '',
    user_type: '',
    message: '',
    
      errors: {
        userName: false,
        userSurname: false,
        card_id: false,
        userInDB: false,
      }
    }
    
    messages = {
      userNameIncorrect: 'Nazwa musi być dłuższa niż 3 znaki', // "i nie może zawierać spacji" - info: TEST
      userSurnameIncorrect: 'Nazwa musi być dłuższa niż 3 znaki',
      card_idIncorrect: 'Numer karty musi zawierać 3 cyfry',
      noUserInDB: 'Błędny numer karty lub dane użytkownika',
    }
    
    handleChange = e => {
      const name = e.target.name;
      const type = e.target.type;
    
      if(type === "text") {
        const value = e.target.value;
        this.setState({
          [name]: value
        })
      }
    }
    
    handleSubmit = e => {
      e.preventDefault();
      const validation = this.formValidation();
      // TODO probably remove or modified when backend is in
      const findUser = this.findUser(this.state.card_id, this.state.userName, this.state.userSurname);
      const park_place_id = this.userParkingSpaceValidation(this.state.card_id, this.state.userName, this.state.userSurname);
      const user_type = this.setUserType(this.state.card_id, this.state.userName, this.state.userSurname);
    
      if(validation.correct) {
        //TODO GET
        if(findUser.length > 0) {
          this.setState({
            userName: '',
            userSurname: '',
            card_id: '',
            message: 'Logowanie...',
            user_type: user_type,
            errors: {
              userName: false,
              userSurname: false,
              card_id: false,
            }
          })

          this.props.logUser(this.state.card_id, this.state.userName, this.state.userSurname);
          
          if(park_place_id === null) {
            this.props.history.push(`/welcome/${this.state.card_id}`);
          } else {
            this.props.history.push(`/final-confirmation/${this.state.card_id}/${park_place_id}`)
          }

          this.setState({
            errors: {
              userInDB: false,
            }
          })          
        } else {
          this.setState({
            errors: {
              userInDB: true,
            }
          })
        }
      } else {
        this.setState({
          errors: {
            userName: !validation.userName,
            userSurname: !validation.userSurname,
            card_id: !validation.card_id,
          }
        })
      }
    }
    
    formValidation = () => {
      let userName = false;
      let userSurname = false;
      let card_id = false;
      let correct = false;
    
      if(this.state.userName.length > 3) { // && this.state.userName.indexOf(' ') === -1 - info TEST
        userName = true;
      }
      if(this.state.userSurname.length > 3) {
        userSurname = true;
      }
      if(this.state.card_id.length === 3 && !isNaN(this.state.card_id)) {
        card_id = true;
      }
      if(userName && userSurname && card_id) {
        correct = true;
      }
    
      return ({
        correct,
        userName,
        userSurname,
        card_id,
      })
    }

    // TODO findUser, userParkingSpaceValidation, setUserType probably remove or modified when backend is in
    findUser(cardNumber, userName, userSurname) {
      return this.props.users.filter(user =>
        user.name.toLowerCase() === userName.toLowerCase()
        && user.surname.toLowerCase() === userSurname.toLowerCase()
        && user.card_id === Number(cardNumber));      
    }

    userParkingSpaceValidation(cardNumber, userName, userSurname) {
      const user = this.findUser(cardNumber, userName, userSurname);
      let place = null; 
      user.map(el => place = el.park_place_id)
      return place;
    }

    setUserType(cardNumber, userName, userSurname) {
      const user = this.findUser(cardNumber, userName, userSurname);
      let type = ''; 
      user.map(el => type = el.user_type)
      return type;
    }
    
    componentDidUpdate() {
      if(this.state.message !== '') {
        setTimeout(() => this.setState({message: ''}), 2000);
      }
    }

    render() {
      return (
        <div className="login-container">
          <div className="login-container__logo">
            <img src={bigLogo} alt="parkando-logo"/>
          </div>
          <form className="login-container__form form" onSubmit={this.handleSubmit} noValidate>
            {this.state.errors.userInDB && <span className="simple-label__error error--top">{this.messages.noUserInDB}</span>}
            <label htmlFor="number" className="form__simple-label simple-label">Podaj nr karty: 
              <input
                type="text"
                id="number"
                name="card_id"
                value={this.state.card_id}
                onChange={this.handleChange}
                className="simple-label__input"
              />
              {this.state.errors.card_id && <span className="simple-label__error">{this.messages.card_idIncorrect}</span>}
            </label>
            <label htmlFor="name" className="form__simple-label simple-label">Imię: 
              <input
                type="text"
                id="name"
                name="userName"
                value={this.state.userName}
                onChange={this.handleChange}
                className="simple-label__input"
              />
              {this.state.errors.userName && <span className="simple-label__error">{this.messages.userNameIncorrect}</span>}
            </label>
            <label htmlFor="surname" className="form__simple-label simple-label">Nazwisko: 
              <input
                type="text"
                id="surname"
                name="userSurname"
                value={this.state.userSurname}
                onChange={this.handleChange}
                className="simple-label__input"
              />
              {this.state.errors.userSurname && <span className="simple-label__error">{this.messages.userSurnameIncorrect}</span>}
            </label>
            <button className="form__login-btn">Zaloguj</button>
          </form>
          {this.state.message && <h3>{this.state.message}</h3>}
        </div>
  )}
}

export default withRouter(LoginPage);
