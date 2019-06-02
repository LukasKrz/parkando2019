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

    componentWillMount() {
        // const type = this.state.user_type.toLowerCase();
        fetch(`/miejsca/dostepnepodstawowe/${this.props.userType}`)
        .then(response => response.json())
          .then(data => {
            let emptySpacesFromBack = [];
            data && data.map(a => emptySpacesFromBack.push(a.parkPlaceId))
            this.setState({emptySpaces: emptySpacesFromBack});
          })
      }

      getOccupiedPlaces = (day) => {
        let occupiedExtraSpaces;
        switch(day) {
            case 1: 
                occupiedExtraSpaces = dayPlacesMap.pon;
                break;
            case 2:
                occupiedExtraSpaces = dayPlacesMap.wt;
                break;
            case 3:
                occupiedExtraSpaces = dayPlacesMap.sr;
                break;
            case 4:
                occupiedExtraSpaces = dayPlacesMap.czw;
                break;
            case 5:
                occupiedExtraSpaces = dayPlacesMap.pt;
                break;
            case 6:
                occupiedExtraSpaces = dayPlacesMap.sob;
                break;
            case 7:
                occupiedExtraSpaces = dayPlacesMap.niedz;
                break;
            default:
                occupiedExtraSpaces = [];
                break;
        }
        
        return occupiedExtraSpaces;
    }

    render() {
        const occupiedSpaces = this.getOccupiedPlaces(Number(this.props.match.params.extra_place));
        console.log('occupiedSpaces: ', occupiedSpaces);
        
        return (
            <section className="parking-choice-container">
                <div className="parking-choice-container__map map">
                    <button className="map__parking-btn parking-btn parking-btn--active parking-btn--active-1" id={1} onClick={this.handleClick}>
                        <span className="parking-btn__number">{
                            Number(this.props.match.params.extra_place) === 0
                            ? this.state.emptySpaces.length
                            : 3 - occupiedSpaces.length
                            
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
