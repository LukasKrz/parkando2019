import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import dayPlacesMap from '../../mocks/dayPlacesMap.js'; // moki!

import emptyPin from '../../images/pusty_pin.png';

class ParkingChoicePage extends Component {
    state = {
        emptySpaces: []
    }

    handleClick = (e) => {
        e.preventDefault();
        this.props.history.push(`/choice/${this.props.match.params.card_id}/${this.props.match.params.extra_place}`)
        // przejscie do wyboru miejsca dodatkowego
        // this.props.history.push(`/choice/${this.props.match.params.card_id}/${this.props.match.params.extra_place}`)
    }

    // componentWillMount() {
    //     fetch(`/miejsca/dostepnepodstawowe/${this.props.userType}`)
    //     .then(response => response.json())
    //       .then(data => {
    //         let emptySpacesFromBack = [];
    //         data && data.map(a => emptySpacesFromBack.push(a.parkPlaceId))
    //         this.setState({emptySpaces: emptySpacesFromBack});
    //       })
    //   }

      getEmptyExtraPlaces = (day) => {
        let emptyExtraSpaces;
        switch(day) {
            case 1: 
                emptyExtraSpaces = dayPlacesMap.pon;
                break;
            case 2:
                emptyExtraSpaces = dayPlacesMap.wt;
                break;
            case 3:
                emptyExtraSpaces = dayPlacesMap.sr;
                break;
            case 4:
                emptyExtraSpaces = dayPlacesMap.czw;
                break;
            case 5:
                emptyExtraSpaces = dayPlacesMap.pt;
                break;
            case 6:
                emptyExtraSpaces = dayPlacesMap.sob;
                break;
            case 7:
                emptyExtraSpaces = dayPlacesMap.niedz;
                break;
            default:
                emptyExtraSpaces = [];
                break;
        }
        return emptyExtraSpaces;
    }

    render() {
        const emptyExtra = this.getEmptyExtraPlaces(Number(this.props.match.params.extra_place));
        console.log('empty from App in ParkingChoice: ', this.props.emptySpaces);
        return (
            <section className="parking-choice-container">
                <div className="parking-choice-container__map map">
                    <button className="map__parking-btn parking-btn parking-btn--active parking-btn--active-1" id={1} onClick={this.handleClick}>
                        <span className="parking-btn__number">{
                            Number(this.props.match.params.extra_place) === 0
                            ? this.props.emptySpaces.length
                            : emptyExtra.length  
                            }</span>
                        <img src={emptyPin} alt='pin wyboru parkingu' />
                    </button>
                    <button className="map__parking-btn parking-btn parking-btn--disabled parking-btn--disabled-1" id={2}>
                        <span className="parking-btn__number">0</span>
                        <img src={emptyPin} alt='pin wyboru parkingu' />
                    </button>
                    <button className="map__parking-btn parking-btn parking-btn--disabled parking-btn--disabled-2" id={3}>
                        <span className="parking-btn__number">0</span>
                        <img src={emptyPin} alt='pin wyboru parkingu' />
                    </button>
                </div>
            </section>
        )
    }
}

export default withRouter(ParkingChoicePage);
