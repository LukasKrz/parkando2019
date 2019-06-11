import React, { Component } from 'react';
import moment from 'moment';

import { withRouter } from "react-router-dom";

// import dayPlacesMap from '../../mocks/dayPlacesMap.js';

import SingleDayButton from './SingleDayButton.js';
import InfoButton from '../InfoModal/InfoButton.js';

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

    // componentWillMount() {
    //     fetch(`/miejsca/dostepnepodstawowe/${this.props.userType}`)
    //     .then(response => response.json())
    //       .then(data => {
    //         let emptySpacesFromBack = [];
    //         data && data.map(a => emptySpacesFromBack.push(a.parkPlaceId))
    //         this.setState({emptySpaces: emptySpacesFromBack});
    //       });
    //     this.setState({extraSpaces: this.getEmptyExtraPlaces(Number(this.props.match.params.extra_place))});
    //   }

    // getDayNumber = (stringDay) => {
    //     switch(stringDay) {
    //         case 'poniedzialek': 
    //             return 1;
    //         case 'wtorek':
    //             return 2;
    //         case 'sroda':
    //             return 3;
    //         case 'czwartek':
    //             return 4;
    //         case 'piatek':
    //             return 5;
    //         case 'sobota':
    //             return 6;
    //         case 'niedziela':
    //             return 7;
    //         default:
    //             return 0
    //     }
    // }

    componentWillMount() {
        //TODO GET from DB, backend
        fetch(`/miejsca/dostepnedodatkowe/${this.props.userType}`)
        .then(response => response.json())
          .then(data => {
              console.log('DATA', data, typeof data, data.niedziela);

            const isAnyFreePlace = this.props.userType === "dzienne"
            ? (data.sobota.length + data.niedziela.length === 0)
            : (data.poniedzialek.length + data.wtorek.length + data.sroda.length + data.czwartek.length + data.piatek.length === 0);
        
            this.setState({
            pon: data.poniedzialek,
                wt: data.wtorek,
                sr: data.sroda,
                czw: data.czwartek,
                pt: data.piatek,
                sob: data.sobota,
                niedz: data.niedziela,
            noPlace: isAnyFreePlace
        })
          });
        // this.setState({extraSpaces: this.getEmptyExtraPlaces(Number(this.props.match.params.extra_place))});

        
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
                <InfoButton history={this.props.history}/>
                <span className="day-choice-container__no-place-info">PRZYKRO NAM, NIE MA DODATKOWYCH WOLNYCH MIEJSC NA NAJBLIŻSZY TYDZIEŃ</span>
                <button
                    className="day-choice-container__log-out-btn"
                    onClick={(e) => {e.preventDefault(); this.props.history.push(`/`)}}
                >
                    WYLOGUJ
                </button>
            </section>
            : <section className="day-choice-container">
                <InfoButton history={this.props.history}/>
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
