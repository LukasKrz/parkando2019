import React, { Component } from 'react';

import { withRouter } from "react-router-dom";
import moment from 'moment';

class FinalConfirmationPage extends Component {    
    render() {
        const date = moment().add(6, 'd').format('DD.MM.YYYY');
        const userName = this.props.userName;
        const userSurname = this.props.userSurname;    
        return (
            <section className="confirmation-container confirmation-container--final-view">
                <div className="confirmation-container__place place">
                    Miejsce <span className="place__number">{this.props.match.params.park_place_id}</span>
                </div>
                <div className="confirmation-container__user">
                    zostało zarezerwowane dla: 
                    <div>{`${userName} ${userSurname}`}</div>
                </div>
                <div className="confirmation-container__expiration expiration">
                    Rezerwacja jest ważna 
                    <div>
                        {this.props.userType === 'Dzienny'
                            ? 'w dni robocze'
                            : 'w weekendy'
                        }
                    </div>
                    do
                    <div className="expiration__date">{date}</div> 
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
