import React, { Component } from 'react';

import classNames from 'classnames';

class SpaceInput extends Component {
    render() {
        return (
            <input 
                className={classNames('map-details__btn-place', 'btn-place', `btn-place--${this.props.number}`, {'btn-place--disabled': this.props.occupied})}
                type="button"
                value={this.props.number}
                id={this.props.number}
                onClick={this.props.handleClick}
            ></input>
        )
    }
}

export default SpaceInput;
