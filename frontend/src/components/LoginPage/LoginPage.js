import React, {Component} from 'react';

import {withRouter} from "react-router-dom";

import bigLogo from '../../images/parkando.png';

class LoginPage extends Component {
    state = {
        userName: '',
        userSurname: '',
        card_id: '',
        user_type: '',
        message: '',
        test: '',

        errors: {
            userName: false,
            userSurname: false,
            card_id: false,
            userInDB: false,
        }
    }

    messages = {
        userNameIncorrect: 'Nazwa musi być dłuższa niż 3 znaki',
        userSurnameIncorrect: 'Nazwa musi być dłuższa niż 3 znaki',
        card_idIncorrect: 'Numer karty musi zawierać 3 cyfry',
        noUserInDB: 'Błędny numer karty lub dane użytkownika',
    }

    handleChange = e => {
        const name = e.target.name;
        const type = e.target.type;

        if (type === "text") {
            const value = e.target.value;
            this.setState({
                [name]: value
            })
        }
    }

    getDayNumber = (stringDay) => {
        switch(stringDay) {
            case 'poniedzialek': 
                return 1;
            case 'wtorek':
                return 2;
            case 'sroda':
                return 3;
            case 'czwartek':
                return 4;
            case 'piatek':
                return 5;
            case 'sobota':
                return 6;
            case 'niedziela':
                return 7;
            default:
                return 0
        }
    }

    handleSubmit = e => {
        e.preventDefault();
        const validation = this.formValidation();

        if (validation.correct) {
            fetch('/api/studenci/walidacja', {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    card_id: this.state.card_id,
                    name: this.state.userName,
                    surname: this.state.userSurname
                })
            })
            .then(response => response.json())
            .then(data => {
                if(data.card_id !== null) {
                    this.setState({
                        userName: data.name,
                        userSurname: data.surname,
                        card_id: data.card_id,
                        user_type: data.user_type,
                        test: data,
                        errors: {
                          userInDB: false
                        }
                    })

                const dayNum = this.getDayNumber(data.dayOfWeek);

                this.props.logUser(data.card_id, data.name, data.surname, data.user_type, data.park_place_id, data.reservationDatePodstawowe, data.parkPlaceIdDodatkowe, dayNum, data.reservationDateDodatkowe);

                if(data.park_place_id === null) {
                    this.props.history.push(`/welcome/${data.card_id}`);
                } else if(data.park_place_id !== null && data.parkPlaceIdDodatkowe === null) {
                    this.props.history.push(`/final-confirmation/${data.card_id}/0/${data.park_place_id}`)
                } else if(data.park_place_id !== null && data.parkPlaceIdDodatkowe !== null) {
                    this.props.history.push(`/two-reservation/${data.card_id}/${dayNum}/${data.parkPlaceIdDodatkowe}`)
                }

            } else {
                this.setState({
                    errors: {
                        userInDB: true,
                    }
                })
                  console.log('NIE MA USERA');  
                }
            })
            .catch(error => {
              console.log(error);
            })
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

        if (this.state.userName.length > 2) {
            userName = true;
        }
        if (this.state.userSurname.length > 2) {
            userSurname = true;
        }
        if (this.state.card_id.length === 3 && !isNaN(this.state.card_id)) {
            card_id = true;
        }
        if (userName && userSurname && card_id) {
            correct = true;
        }

        return ({
            correct,
            userName,
            userSurname,
            card_id,
        })
    }
    
    componentDidUpdate() {
        if (this.state.message !== '') {
            setTimeout(() => this.setState({message: ''}), 2000);
        }
    }

    render() {
        return (
            <div className="login-container">
                <div className="login-container__logo">
                    <img src={bigLogo} alt="parkando-logo" />
                </div>
                <form className="login-container__form form" onSubmit={this.handleSubmit} noValidate>
                    { this.state.errors.userInDB 
                        && <span className="simple-label__error error--top">
                                {this.messages.noUserInDB}
                            </span>
                    }
                    <label htmlFor="number" className="form__simple-label simple-label">
                        Podaj nr karty:
                        <input type="text" id="number" name="card_id" value={this.state.card_id} onChange={this.handleChange} className="simple-label__input" />
                    { this.state.errors.card_id
                        && <span className="simple-label__error">
                                {this.messages.card_idIncorrect}
                            </span>}
                    </label>
                    <label htmlFor="name" className="form__simple-label simple-label">
                        Imię:
                        <input type="text" id="name" name="userName" value={this.state.userName} onChange={this.handleChange} className="simple-label__input" />
                    { this.state.errors.userName
                        && <span className="simple-label__error">
                                {this.messages.userNameIncorrect}
                            </span>}
                    </label>
                    <label htmlFor="surname" className="form__simple-label simple-label">
                        Nazwisko:
                        <input type="text" id="surname" name="userSurname" value={this.state.userSurname} onChange={this.handleChange} className="simple-label__input" />
                    { this.state.errors.userSurname
                        && <span className="simple-label__error">
                                {this.messages.userSurnameIncorrect}
                            </span>}
                    </label>
                    <button className="form__login-btn">Zaloguj</button>
                </form>
                { this.state.message && <h3>{this.state.message}</h3>}
            </div>
        )
    }
}

export default withRouter(LoginPage);
