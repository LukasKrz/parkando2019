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
                <div className={classNames('modal-overlay', {'modal-overlay--visible': this.state.isModalOpen})}>
                    <div className={classNames('modal', {'modal--visible': this.state.isModalOpen})}>
                        <p>Kontakt z uczelnią: <span>pomoc@library.put.poznan.pl</span></p>
                        <p>Kontakt z autorami aplikacji: <span>contact@parkando.com</span></p>
                        <button
                            className="modal__log-out-btn"
                            onClick={(e) => {e.preventDefault(); this.props.history.push(`/`)}}
                            >
                            WYLOGUJ
                        </button>
                    </div>
                </div>
            </>
        )
    }
}

export default InfoButton;
