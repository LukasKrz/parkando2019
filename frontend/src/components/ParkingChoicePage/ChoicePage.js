import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import dayPlacesMap from '../../mocks/dayPlacesMap.js';

import SpaceInput from './SpaceInput.js';
import InfoButton from '../InfoModal/InfoButton.js';

class ChoicePage extends Component {
    state = {
        emptySpaces: [],
        extraSpaces: []
    }

    handleClick = (e) => {
        e.preventDefault();
        const number = e.target.id;
        const occupiedSlot = this.checkIfParkingIsOccupied(Number(number));
        if(!occupiedSlot) {
            this.props.history.push(`/confirmation/${this.props.match.params.card_id}/${this.props.match.params.extra_place}/${number}`)    
        } else {
            console.log('miejsce jest zajęte: ', number);
        }        
    }

    componentWillMount() {
        fetch(`/miejsca/dostepnepodstawowe/${this.props.userType}`)
        .then(response => response.json())
          .then(data => {
            let emptySpacesFromBack = [];
            data.map(a => emptySpacesFromBack.push(a.parkPlaceId))
            this.setState({emptySpaces: emptySpacesFromBack});
          })
          this.setState({extraSpaces: this.getEmptyExtraPlaces(Number(this.props.match.params.extra_place))});
          // this.getEmptyExtraPlaces(1);
      }

    checkIfParkingIsOccupied = (number) => {
        return Number(this.props.match.params.extra_place) === 0
        ? !this.state.emptySpaces.some(space => space === number)
        : !this.state.extraSpaces.some(space => space === number)
    }

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

        this.setState({extraSpaces: emptyExtraSpaces});
        return emptyExtraSpaces;
    }

    render() {
        console.log('EXTRA PLACES: ', this.state.emptyExtraSpaces);
        console.log('CHECK IF extra_place === 0', Number(this.props.match.params.extra_place) === 0);
         
        return (
            <section className="choice-page-container">
                <InfoButton />
                <div className="choice-page-container__map-details map-details">
                <SpaceInput
                        number={10}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) === 0
                            ? true
                            : this.checkIfParkingIsOccupied(10)
                        }
                    />
                    <SpaceInput
                        number={9}
                        handleClick={this.handleClick}
                        occupied={
                           this.checkIfParkingIsOccupied(9)
                        }
                    />
                    <SpaceInput
                        number={8}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) === 0
                            ? true
                            : this.checkIfParkingIsOccupied(8)
                        }
                    />
                    <SpaceInput
                        number={7}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) !== 0
                            ? true
                            : this.checkIfParkingIsOccupied(7)
                        }
                    />
                    <SpaceInput
                        number={6}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) !== 0
                            ? true
                            : this.checkIfParkingIsOccupied(6)
                        }
                    />
                    <SpaceInput
                        number={5}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) !== 0
                            ? true
                            : this.checkIfParkingIsOccupied(5)
                        }
                    />
                    <SpaceInput
                        number={4}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) !== 0
                            ? true
                            : this.checkIfParkingIsOccupied(4)
                        }
                    />
                    <SpaceInput
                        number={3}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) !== 0
                            ? true
                            : this.checkIfParkingIsOccupied(3)
                        }
                    />
                    <SpaceInput
                        number={2}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) !== 0
                            ? true
                            : this.checkIfParkingIsOccupied(2)
                        }
                    />
                    <SpaceInput
                        number={1}
                        handleClick={this.handleClick}
                        occupied={
                            Number(this.props.match.params.extra_place) !== 0
                            ? true
                            : this.checkIfParkingIsOccupied(1)
                        }
                    />
                </div>
        </section>
        )
    }
}

export default withRouter(ChoicePage);
