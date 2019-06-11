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

          fetch(`/miejsca/dostepnedodatkowe/${this.props.userType}`)
          .then(response => response.json())
            .then(data => {
                console.log('DATA', data, typeof data, data.sobota);
        this.setState({extraSpaces: this.getEmptyExtraPlaces(data, Number(this.props.match.params.extra_place))}); //TODO -> 1 to poniedzialek

        // this.setState({extraSpaces: data.sobota}); //TODO -> 1 to poniedzialek

            });

        //   this.setState({extraSpaces: this.getEmptyExtraPlaces(Number(this.props.match.params.extra_place))});
      }

    checkIfParkingIsOccupied = (number) => {
        return Number(this.props.match.params.extra_place) === 0
        ? !this.state.emptySpaces.some(space => space === number)
        : !this.state.extraSpaces.some(space => space === number)
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

        this.setState({extraSpaces: emptyExtraSpaces});
        return emptyExtraSpaces;
    }

    render() {
        return (
            <section className="choice-page-container">
                <InfoButton history={this.props.history}/>
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
