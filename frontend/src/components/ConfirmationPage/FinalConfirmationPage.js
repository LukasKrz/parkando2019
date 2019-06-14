import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import InfoButton from '../InfoModal/InfoButton.js';

class FinalConfirmationPage extends Component {    
    render() {
        // console.log('data podsta: ', this.props.reservationDatePodstawowe);
        // console.log('data dodatkowa: ', this.props.reservationDateDodatkowe);

        return (
            <section className="confirmation-container confirmation-container--final-view">
                <InfoButton history={this.props.history}/>
                <div className="confirmation-container__place place">
                    Miejsce <span className="place__number">{this.props.match.params.park_place_id}</span>
                </div>
                <div className="confirmation-container__user">
                    zostało zarezerwowane dla: 
                    <div>{`${this.props.userName} ${this.props.userSurname}`}</div>
                </div>
                <div className="confirmation-container__expiration expiration">
                    Rezerwacja jest ważna 
                    <div>
                        {this.props.userType === 'dzienne'
                            ? 'od pon do pt'
                            : 'w sob i niedz'
                        }
                    </div>
                    do
                    <div className="expiration__date">{this.props.expirationDate}</div>
                    {/* TODO data z backendu */} 
                </div>
                <div className="confirmation-container__enjoy">Miłego parkowania!</div>
                <div className="confirmation-container__enjoy">
                    {`* Dodatkowo możesz zarezerwować miejsce na jeden dzień
                    ${this.props.userType === 'dzienne'
                        ? ' w sob lub niedz '
                        : ' od pon do pt '
                    }`}
                </div>
                <div className="confirmation-container__buttons-section buttons-section">
                    <button
                        className="buttons-section__log-out-btn"
                        onClick={(e) => {e.preventDefault(); this.props.history.push(`/`)}}
                        >
                        WYLOGUJ
                    </button>
                    <button
                        className="buttons-section__additional-place"
                        onClick={(e) => {e.preventDefault(); this.props.history.push(`/day-choice/${this.props.match.params.card_id}`)}}
                    >
                        DODATKOWE MIEJSCE
                    </button>
                </div>
            </section>
        )
    }
}

export default withRouter(FinalConfirmationPage);
