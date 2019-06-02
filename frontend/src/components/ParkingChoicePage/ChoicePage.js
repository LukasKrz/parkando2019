import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import SpaceInput from './SpaceInput.js';

class ChoicePage extends Component {
    state = {
        emptySpaces: []
    }

    handleClick = (e) => {
        //TODO POST
        e.preventDefault();
        const number = e.target.id;

        fetch(`/miejsca/rezerwacjapodstawowe/${this.props.userType}/${number}`, {
            method: 'PUT',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                card_id: this.props.cardId,
            })
        }).then(response => response.json())
            .then(data => {
                this.props.choiceHandler(number);
                console.log('WYSŁANE', data);
            })
            .catch(error => {
                console.log('ERROR: ', error);
            })

       
        const occupiedSlot = this.checkIfParkingIsOccupied(Number(number));
        if(!occupiedSlot) {
            this.props.history.push(`/confirmation/${this.props.match.params.card_id}/${number}`)    
        } else {
            console.log('miejsce jest zajęte: ', number);
        }        
    }

    componentWillMount() {
        // const type = this.state.user_type.toLowerCase();
        fetch(`/miejsca/dostepnepodstawowe/${this.props.userType}`)
        .then(response => response.json())
          .then(data => {
            console.log('TYP: ', this.props.userType);
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
        return !this.state.emptySpaces.some(space => space === number);
    }

    render() {
        return (
            <section className="choice-page-container">
                <div className="choice-page-container__map-details map-details">
                    <SpaceInput
                        number={7}
                        emptySpaces={this.props.emptySpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(7)}
                    />
                    <SpaceInput
                        number={6}
                        emptySpaces={this.props.emptySpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(6)}
                    />
                    <SpaceInput
                        number={5}
                        emptySpaces={this.props.emptySpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(5)}
                    />
                    <SpaceInput
                        number={4}
                        emptySpaces={this.props.emptySpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(4)}
                    />
                    <SpaceInput
                        number={3}
                        emptySpaces={this.props.emptySpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(3)}
                    />
                    <SpaceInput
                        number={2}
                        emptySpaces={this.props.emptySpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(2)}
                    />
                    <SpaceInput
                        number={1}
                        emptySpaces={this.props.emptySpaces}
                        handleClick={this.handleClick}
                        occupied={this.checkIfParkingIsOccupied(1)}
                    />
                </div>
            </section>
        )
    }
}

export default withRouter(ChoicePage);
