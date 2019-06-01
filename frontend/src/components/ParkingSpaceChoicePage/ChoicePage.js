import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import SpaceInput from './SpaceInput.js';

class ChoicePage extends Component {
    handleClick = (e) => {
        e.preventDefault();
        const number = e.target.id;
        this.props.choiceHandler(number);
        const occupiedSlot = this.checkIfParkingIsOccupied(Number(number));
        if(!occupiedSlot) {
            this.props.history.push(`/confirmation/${this.props.match.params.card_id}/${number}`)    
        } else {
            console.log('miejsce jest zajęte: ', number);
        }        
    }

    checkIfParkingIsOccupied = (number) => {
        return this.props.occupiedSpaces.some(space => space === number);
    }

    render() {
        return (
            <section className="choice-page-container">
                <div className="choice-page-container__map-details map-details">
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
            </section>
        )
    }
}

export default withRouter(ChoicePage);
