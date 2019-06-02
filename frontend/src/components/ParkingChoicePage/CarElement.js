import React, { Component } from 'react';

import classNames from 'classnames';

class CarElement extends Component {
    render() {
        return (
            <div
            className={classNames('map-details__btn-place', 'btn-place', 'car-element', `btn-place--${this.props.number}`)}
            />
        )
    }
}

export default CarElement;
