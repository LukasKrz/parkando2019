import React, { Component } from 'react';

import { withRouter } from "react-router-dom";
// import moment from 'moment';

class ConfirmationPage extends Component {
    // state = {
    //     confirm: false,
    // }

    // setConfirm = () => {
    //     this.setState({
    //       confirm: true
    //     })
    //   }

    handleClick = (e) => {
        e.preventDefault();
        const number = this.props.match.params.park_place_id;
        this.props.choiceHandler(this.props.match.params.park_place_id);
        console.log('test', number);
        this.props.history.push(`/final-confirmation/${this.props.match.params.card_id}/${number}`)
        fetch(`/miejsca/rezerwacjapodstawowe/${this.props.userType}/${number}`, {
            method: 'PUT',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                card_id: this.props.cardId,
            })
        }).then(response => response.json())
            .then(data => {
                console.log('WYSŁANE', data);
            })
            .catch(error => {
                console.log('ERROR: ', error);
            })
    }

    render() {
        // back to this concept when different view for user with parking place from begining
        // const date = moment().format('DD.MM.YYYY');
        // const userName = this.props.userName;
        // const userSurname = this.props.userSurname;    
        return (
            <section className="confirmation-container">
                Wybrałeś miejsce:
                <div className="confirmation-container__number">{this.props.match.params.park_place_id}</div>
                <div className="confirmation-container__buttons-section buttons-section">
                    <button
                        className="buttons-section__log-out-btn"
                        onClick={this.handleClick}
                    >
                        ZATWIERDŹ
                    </button>
                    <button
                        className="buttons-section__back-btn"
                        onClick={(e) => {e.preventDefault(); this.props.history.push(`/choicePaking/${this.props.match.params.card_id}`)}}
                    >
                        COFNIJ
                    </button>
                </div>
            </section>
        )
    }
}

export default withRouter(ConfirmationPage);
