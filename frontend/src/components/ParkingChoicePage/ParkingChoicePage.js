import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import emptyPin from '../../images/pusty_pin.png';
import InfoButton from '../InfoModal/InfoButton.js';

class ParkingChoicePage extends Component {
    state = {
        emptySpaces: [],
        extraSpaces: []
    }

    handleClick = (e) => {
        e.preventDefault();
        this.props.history.push(`/choice/${this.props.match.params.card_id}/${this.props.match.params.extra_place}`)
    }

    componentWillMount() {
        fetch(`/miejsca/dostepnepodstawowe/${this.props.userType}`)
            .then(response => response.json())
            .then(data => {
                let emptySpacesFromBack = [];
                data && data.map(a => emptySpacesFromBack.push(a.parkPlaceId))
                this.setState({emptySpaces: emptySpacesFromBack});
            });

          fetch(`/miejsca/dostepnedodatkowe/${this.props.userType}`)
            .then(response => response.json())
            .then(data => {
                this.setState({extraSpaces: this.getEmptyExtraPlaces(data, Number(this.props.match.params.extra_place))});
            });
      }

      getEmptyExtraPlaces = (data, day) => {
        let emptyExtraSpaces;
        switch(day) {
            case 1: 
                emptyExtraSpaces = data.poniedzialek;
                break;
            case 2:
                emptyExtraSpaces = data.wtorek;
                break;
            case 3:
                emptyExtraSpaces = data.sroda;
                break;
            case 4:
                emptyExtraSpaces = data.czwartek;
                break;
            case 5:
                emptyExtraSpaces = data.piatek;
                break;
            case 6:
                emptyExtraSpaces = data.sobota;
                break;
            case 7:
                emptyExtraSpaces = data.niedziela;
                break;
            default:
                emptyExtraSpaces = [];
                break;
        }
        return emptyExtraSpaces;
    }

    render() {
        // console.log('EMPTY: ', this.state.emptySpaces, ' EXTRA: ', this.state.extraSpaces);
        
        return (
            <section className="parking-choice-container">
                <InfoButton history={this.props.history}/>
                <div className="parking-choice-container__map map">
                    <button className="map__parking-btn parking-btn parking-btn--active parking-btn--active-1" id={1} onClick={this.handleClick}>
                        <span className="parking-btn__number">{
                            Number(this.props.match.params.extra_place) === 0
                            ? this.state.emptySpaces.length
                            : this.state.extraSpaces.length  
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
