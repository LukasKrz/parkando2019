import React, { Component } from 'react';
import moment from 'moment';

import { withRouter } from "react-router-dom";

class WelcomePage extends Component {
    render() {
        return (
            <section className="welcome-container">
                Witaj,
                <div className="welcome-container__user-name">{this.props.userName}!</div>
                <div className="welcome-container__user-type">{`Tryb studiów: ${this.props.userType}`}</div>
                <div className="welcome-container__user-type">{`Zarezerwuj miejsce ${this.props.userType === 'dzienne' ? 'od poniedziałku do piątku' : 'na sobotę i niedzielę'}`}</div>
                <div className="welcome-container__user-type">Rezerwacja będzie ważna do <span>{moment().add(6, 'd').format('DD.MM.YYYY')}</span></div>
                <button
                    className="welcome-container__go-btn"
                    onClick={(e) => {e.preventDefault(); this.props.history.push(`/choicePaking/${this.props.match.params.card_id}`)}}
                >
                    Wybierz miejsce
                </button>
            </section>
        )
    }
}

export default withRouter(WelcomePage);
