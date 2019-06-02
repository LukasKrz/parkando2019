import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import dayPlacesMap from '../../mocks/dayPlacesMap.js';

import SpaceInput from './SpaceInput.js';

class ChoicePage extends Component {
    state = {
        emptySpaces: [],
        extraSpaces: []
    }

    handleClick = (e) => {
        //TODO POST
        e.preventDefault();
        const number = e.target.id;
        // this.props.choiceHandler(number);

        // fetch(`/miejsca/rezerwacjapodstawowe/${this.props.userType}/${number}`, {
        //     method: 'PUT',
        //     headers: {
        //         Accept: 'application/json',
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({
        //         card_id: this.props.cardId,
        //     })
        // }).then(response => response.json())
        //     .then(data => {
        //         
        //         console.log('WYSŁANE', data);
        //     })
        //     .catch(error => {
        //         console.log('ERROR: ', error);
        //     })

       
        const occupiedSlot = this.checkIfParkingIsOccupied(Number(number));
        if(!occupiedSlot) {
            this.props.history.push(`/confirmation/${this.props.match.params.card_id}/${this.props.match.params.extra_place}/${number}`)    
        } else {
            console.log('miejsce jest zajęte: ', number);
        }        
    }

    componentWillMount() {
        // const type = this.state.user_type.toLowerCase();
        fetch(`/miejsca/dostepnepodstawowe/${this.props.userType}`)
        .then(response => response.json())
          .then(data => {
            // console.log('TYP: ', this.props.userType);
            console.log('LISTA: ', data);
            let emptySpacesFromBack = [];
            data.map(a => emptySpacesFromBack.push(a.parkPlaceId))
            console.log('WYBRANE: ', emptySpacesFromBack);
 
            this.setState({emptySpaces: emptySpacesFromBack});
          })
      }

    checkIfParkingIsOccupied = (number) => {
        console.log('NUM: ', number);
        
        // return this.props.emptySpaces.some(space => space !== number);
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
        return (
            <section className="choice-page-container">
            {Number(this.props.match.params.extra_place) === 0
                ? <div className="choice-page-container__map-details map-details">
                    <SpaceInput
                        number={7}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(7)}
                    />
                    <SpaceInput
                        number={6}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(6)}
                    />
                    <SpaceInput
                        number={5}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(5)}
                    />
                    <SpaceInput
                        number={4}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(4)}
                    />
                    <SpaceInput
                        number={3}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(3)}
                    />
                    <SpaceInput
                        number={2}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(2)}
                    />
                    <SpaceInput
                        number={1}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(1)}
                    />
                </div>
                : <div className="choice-page-container__map-details map-details map-details--extra-places">
                    <SpaceInput
                        number={8}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(8)}
                    />
                    <SpaceInput
                        number={9}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(9)}
                    />
                    <SpaceInput
                        number={10}
                        occupiedSpaces={this.props.occupiedSpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(10)}
                    />
                </div>
            }

        </section>
        )
    }
}

export default withRouter(ChoicePage);
