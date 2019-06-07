import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

class ConfirmationPage extends Component {
    handleClick = (e) => {
        e.preventDefault();
        const number = this.props.match.params.park_place_id;
        Number(this.props.match.params.extra_place) === 0 && this.props.choiceHandler(number);
        console.log('test', number);
        this.props.history.push(
            Number(this.props.match.params.extra_place) === 0
            ? `/final-confirmation/${this.props.match.params.card_id}/${this.props.match.params.extra_place}/${number}`
            : `/two-reservation/${this.props.match.params.card_id}/${this.props.match.params.extra_place}/${number}`
        )
        if(Number(this.props.match.params.extra_place) === 0) {
            fetch(`/miejsca/rezerwacjapodstawowe/${this.props.userType}/${number}`, {
                method: 'PUT',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    card_id: this.props.cardId,
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('WYSŁANE', data);
                })
                .catch(error => {
                    console.log('ERROR: ', error);
                })
        }
    }

    render() {
        const extraPlace = Number(this.props.match.params.extra_place);
        console.log('EXTRA PLACE: ', extraPlace);
        
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
                        onClick={(e) => {e.preventDefault(); this.props.history.push(`/choicePaking/${this.props.match.params.card_id}/0`)}} // COME BACK!!!
                    >
                        COFNIJ
                    </button>
                </div>
            </section>
        )
    }
}

export default withRouter(ConfirmationPage);
