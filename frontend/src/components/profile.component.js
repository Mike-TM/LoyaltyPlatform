import React, { Component } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import "./profilo.css"

export default class ProfileComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            redirect: null,
            userReady: false,
            currentUser: { username: "" }
        };
    }

    componentDidMount() {
        const currentUser = AuthService.getCurrentUser();

        if (!currentUser) this.setState({ redirect: "/home" });
        this.setState({ currentUser: currentUser, userReady: true })
    }

    render() {
        if (this.state.redirect) {
            return <Navigate to={this.state.redirect} />
        }

        const { currentUser } = this.state;

        return (
            <div className="container">
                {(this.state.userReady) ?
                    <div>
                        <div className="text-center">
                            <h3 className="profile-title">
                                <strong>{currentUser.username}</strong>
                            </h3>
                        </div>
                        <div className="row">
                            <div className="col-md-6 offset-md-3">
                                <div className="profile-info">
                                    <p>
                                        <strong className="profile-label">Id Utente:</strong>{" "}
                                        {currentUser.id}
                                    </p>
                                    <p>
                                        <strong className="profile-label">Email:</strong>{" "}
                                        {currentUser.email}
                                    </p>
                                    <strong className="profile-label">Ruoli:</strong>
                                    <ul>
                                        {currentUser.roles &&
                                            currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div> : null}
            </div>
        );
    }
}