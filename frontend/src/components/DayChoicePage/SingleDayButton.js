import React, { Component } from 'react';
import classNames from 'classnames';

class SingleDayButton extends Component {
    render() {
        const dayName = ['poniedziałek', 'wtorek', 'środa', 'czwartek', 'piątek', 'sobota', 'niedziela'];
        return (
            <button
                className={classNames('day-buttons__day-btn', 'day-btn', {'day-btn--disabled' : this.props.isNoPlace})}
                id={this.props.dayNum}
                onClick={this.props.handleClick}
            >
                {dayName[this.props.dayNum - 1]}
            </button>
        )
    }
}

export default SingleDayButton;
