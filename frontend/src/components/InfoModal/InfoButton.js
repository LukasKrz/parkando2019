import React, { Component } from 'react';
import classNames from 'classnames';

class InfoButton extends Component {
    state = {
        isModalOpen: false,
    }

    showModal = () => {
        this.setState({isModalOpen: !this.state.isModalOpen})
    }
    render() {
        return (
            <>
                <button
                className={classNames('info-btn', {'info-btn--close-sign': this.state.isModalOpen})}
                onClick={this.showModal}
                >
                    {this.state.isModalOpen && 'X'}
                </button>
                <div className={classNames('modal', {'modal--visible': this.state.isModalOpen})}>
                    Kontakt: 
                    WYLOGUJ
                    ZAMKNIJ
                </div>
            </>
        )
    }
}

export default InfoButton;
