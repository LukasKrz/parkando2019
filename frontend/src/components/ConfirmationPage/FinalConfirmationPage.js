import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

class FinalConfirmationPage extends Component {    
    render() {
        return (
            <section className="confirmation-container confirmation-container--final-view">
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
                        {this.props.userType === 'Dzienny'
                            ? 'od pon do pt'
                            : 'w sob i niedz'
                        }
                    </div>
                    do
                    <div className="expiration__date">{this.props.expirationDate}</div> 
                </div>
                <div className="confirmation-container__enjoy">Miłego parkowania!</div>
                <button
                    className="buttons-section__log-out-btn"
                    onClick={(e) => {e.preventDefault(); this.props.history.push(`/`)}}
                >
                    WYLOGUJ
                </button>
            </section>
        )
    }
}

export default withRouter(FinalConfirmationPage);
