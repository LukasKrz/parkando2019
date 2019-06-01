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
                        onClick={(e) => {e.preventDefault(); this.props.history.push(`/final-confirmation/${this.props.match.params.card_id}/${this.props.match.params.park_place_id}`)}}
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
