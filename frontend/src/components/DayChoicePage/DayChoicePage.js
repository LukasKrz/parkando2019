import React, { Component } from 'react';
import moment from 'moment';

import { withRouter } from "react-router-dom";

import dayPlacesMap from '../../mocks/dayPlacesMap.js';

import SingleDayButton from './SingleDayButton.js';

class DayChoicePage extends Component {
    state = {
        pon: [],
        wt: [],
        sr: [],
        czw: [],
        pt: [],
        sob: [],
        niedz: [],
        selectedDay: '',
        noPlace: false,
    }

    componentWillMount() {
        //TODO GET from DB, backend
        console.log('dayPlacesMap: ', dayPlacesMap.pon);
        
        const isAnyFreePlace = this.props.userType === "dzienne"
            ? (dayPlacesMap.sob.length + dayPlacesMap.niedz.length === 0)
            : (dayPlacesMap.pon.length + dayPlacesMap.wt.length + dayPlacesMap.sr.length + dayPlacesMap.czw.length + dayPlacesMap.pt.length === 0);
        
            this.setState({
            pon: dayPlacesMap.pon,
            wt: dayPlacesMap.wt,
            sr: dayPlacesMap.sr,
            czw: dayPlacesMap.czw,
            pt: dayPlacesMap.pt,
            sob: dayPlacesMap.sob,
            niedz: dayPlacesMap.niedz,
            noPlace: isAnyFreePlace
        })
    }

    handleClick = (e) => {
        e.preventDefault();
        // set day in App
        console.log('KLIK', e.target);
        if(!e.target.classList.contains('day-btn--disabled')){
            this.setState({
                selectedDay: e.target.id
            })
            this.props.history.push(`/choicePaking/${this.props.match.params.card_id}/${e.target.id}`)    
        } else {
            console.log('nie ma miejsc');  
        }
    }

    render() {
        return (
            this.state.noPlace
            ? <section className="day-choice-container">
                <span className="day-choice-container__no-place-info">PRZYKRO NAM, NIE MA DODATKOWYCH WOLNYCH MIEJSC NA NAJBLIŻSZY TYDZIEŃ</span>
                <button
                    className="day-choice-container__log-out-btn"
                    onClick={(e) => {e.preventDefault(); this.props.history.push(`/`)}}
                >
                    WYLOGUJ
                </button>
            </section>
            : <section className="day-choice-container">
                Wybierz dzień
                <p className="day-choice-container__date">
                    {`rezerwacja parkingu na jeden dzień tygodnia ważna do ${moment().add(6, 'd').format('DD.MM.YYYY')}`}
                </p>
                {this.props.userType === "dzienne"
                    ? <div className="day-choice-container__day-buttons day-buttons">
                        <SingleDayButton
                            isNoPlace={this.state.sob.length === 0}
                            dayNum={6}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={this.state.niedz.length === 0}
                            dayNum={7}
                            handleClick={this.handleClick}
                        />
                    </div>
                    : <div className="day-choice-container__day-buttons day-buttons">
                        <SingleDayButton
                            isNoPlace={this.state.pon.length === 0}
                            dayNum={1}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={this.state.wt.length === 0}
                            dayNum={2}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={this.state.sr.length === 0}
                            dayNum={3}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={this.state.czw.length === 0}
                            dayNum={4}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={this.state.pt.length === 0}
                            dayNum={5}
                            handleClick={this.handleClick}
                        />
                    </div>
                }
            </section>
        )
    }
}

export default withRouter(DayChoicePage);
