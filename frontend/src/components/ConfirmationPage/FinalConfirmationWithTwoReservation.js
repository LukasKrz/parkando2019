import React, { Component } from 'react';
import moment from 'moment';

import { withRouter } from "react-router-dom";

class FinalConfirmationWithTwoReservation extends Component {
    getDayName = (dayNumber) => {
        let dayName = '';
        switch(dayNumber) {
            case 1: 
                dayName = 'w poniedziałek';
                break;
            case 2:
                dayName = 'we wtorek';
                break;
            case 3:
                dayName = 'w środę';
                break;
            case 4:
                dayName = 'w czwartek';
                break;
            case 5:
                dayName = 'w piątek';
                break;
            case 6:
                dayName = 'w sobotę';
                break;
            case 7:
                dayName = 'w niedzielę';
                break;
            default:
                dayName = '';
                break;
        }
        return dayName;
    }

    getExperienceDateForExactDay = (dayNum) => {
        const today = moment().isoWeekday();
        if (today <= dayNum) { 
        return moment().isoWeekday(dayNum);
        } else {
        return moment().add(1, 'weeks').isoWeekday(dayNum);
        }
    }

    render() {
        const extraDate = this.getExperienceDateForExactDay(Number(this.props.match.params.extra_place)).format('DD.MM.YYYY');        
        return (
            <section className="confirmation-container confirmation-container--two-reservation">
                <div className="confirmation-container__user user">
                    Aktualne rezerwacje dla:
                    <div>{`${this.props.userName} ${this.props.userSurname}`}</div>
                </div>
                <div className="confirmation-container__place place">       
                    Miejsce <span className="place__number">{this.props.placeId}</span>
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
                <div className="confirmation-container__place place place--extra-place">       
                    Dodatkowe miejsce <span className="place__number">{this.props.match.params.park_place_id}</span>
                </div>
                <div className="confirmation-container__expiration expiration">
                    Rezerwacja jest ważna
                    <div>
                        {this.getDayName(Number(this.props.match.params.extra_place))}
                    </div>
                    <div className="expiration__date">{extraDate}</div> 
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