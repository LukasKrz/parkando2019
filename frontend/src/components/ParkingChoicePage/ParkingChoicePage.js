import React, { Component } from 'react';

import { withRouter } from "react-router-dom";

import emptyPin from '../../images/pusty_pin.png';

class ParkingChoicePage extends Component {
    state = {
        emptySpaces: []
    }

    handleClick = (e) => {
        e.preventDefault();
        this.props.history.push(`/choice/${this.props.match.params.card_id}`)
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

    render() {
        return (
            <section className="parking-choice-container">
                <div className="parking-choice-container__map map">
                    <button className="map__parking-btn parking-btn parking-btn--active parking-btn--active-1" id={1} onClick={this.handleClick}>
                        <span className="parking-btn__number">{this.state.emptySpaces.length}</span>
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
