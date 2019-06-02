import React, { Component } from 'react';

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
        const isAnyFreePlace = this.props.userType === "Dzienny"
            ? dayPlacesMap.sob.length + dayPlacesMap.niedz.length === 6
            : dayPlacesMap.pon.length + dayPlacesMap.wt.length + dayPlacesMap.sr.length + dayPlacesMap.czw.length + dayPlacesMap.pt.length === 15;
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
            <section className="day-choice-container">
                Wybierz dzień
                {this.props.userType === "Dzienny"
                    ? <div className="day-choice-container__day-buttons day-buttons">
                        <SingleDayButton
                            isNoPlace={3 - this.state.sob.length === 0}
                            dayNum={6}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={3 - this.state.niedz.length === 0}
                            dayNum={7}
                            handleClick={this.handleClick}
                        />
                    </div>
                    : <div className="day-choice-container__day-buttons day-buttons">
                        <SingleDayButton
                            isNoPlace={3 - this.state.pon.length === 0}
                            dayNum={1}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={3 - this.state.wt.length === 0}
                            dayNum={2}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={3 - this.state.sr.length === 0}
                            dayNum={3}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={3 - this.state.czw.length === 0}
                            dayNum={4}
                            handleClick={this.handleClick}
                        />
                        <SingleDayButton
                            isNoPlace={3 - this.state.pt.length === 0}
                            dayNum={5}
                            handleClick={this.handleClick}
                        />
                    </div>
                }
                {this.state.noPlace && <span>PRZYKRO NAM NIE MA JUŻ WOLNYCH MIEJSC</span>}
            </section>
        )
    }
}

export default withRouter(DayChoicePage);
