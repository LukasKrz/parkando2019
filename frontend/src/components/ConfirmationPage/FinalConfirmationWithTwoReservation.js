import React, { Component } from 'react';
import moment from 'moment';

import { withRouter } from "react-router-dom";

class FinalConfirmationWithTwoReservation extends Component {    
    render() {
        const date = moment().add(6, 'd').format('DD.MM.YYYY');
        return (
            <section className="two-reservation">
                <div className="confirmation-container__user">
                    Aktualne rezerwacje dla:
                    <div>{`${this.props.userName} ${this.props.userSurname}`}</div>
                </div>
                <div className="confirmation-container__place place">       
                    Miejsce <span className="place__number">{this.props.match.params.park_place_id}</span>
                </div>
                <div className="confirmation-container__expiration expiration">
                    Rezerwacja jest ważna w
                    <div>
                        {this.props.match.params.extra_place}
                    </div>
                    do
                    <div className="expiration__date">{date}</div> 
                </div>
                <div className="confirmation-container__place place">       
                    Miejsce <span className="place__number">{this.props.placeId}</span>
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
                <div className="confirmation-container__buttons-section buttons-section">
                    <button
                        className="buttons-section__log-out-btn"
                        onClick={(e) => {e.preventDefault(); this.props.history.push(`/`)}}
                        >
                        WYLOGUJ
                    </button>
                </div>
            </section>
        )
    }
}

export default withRouter(FinalConfirmationWithTwoReservation);