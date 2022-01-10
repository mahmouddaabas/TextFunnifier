import './Styles.css';
import { Container } from 'reactstrap';
import React, { Component } from 'react';

export class App extends Component {
    static displayName = App.name;

    constructor(props) {
        super(props);
        this.state = {output: '', publish: false};
    }

    SendMessage(input) {
        var check = document.getElementById("checkbox");
        if(check.checked){
            this.state.publish = true
        }
        else {
            this.state.publish = false
        }
         let objectJS = { message: input, publish: this.state.publish };
         let objectJSON = JSON.stringify(objectJS);
         fetch("http://localhost:8080/pirateify", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: objectJSON
         }).then(response => {
            if (!response.ok) {
                throw Error(response.status + " " + response.statusText);
            }
            return response.json();
         }).then(responseJSON => {
            this.setState({ output: responseJSON.message});
         }).catch(error => {
            console.log(error.message);
            alert("Error message: " + error.message);
         });
     }

     FlipPublish() {
        this.setState({publish: !this.state.publish});
     }

    render() {
        const Checkbox = ({ label, value, onChange }) => {
            return (
                <label>
                    <input id="checkbox" type="checkbox" checked={value} onChange={onChange} />
                    {label}
                </label>
            );
        };

        return (
            <div>
                <Container>
                    <br />
                    <div className="box-static" style={{ display: "block" }}>
                        <span>
                            Hello there! This is an application that transforms your regular text into pirate text!
                        </span>
                    </div>
                    <br />
                    <div className="box-static" style={{ display: "block" }}>
                        <h5>
                            Input
                        </h5>
                        {Checkbox("publish", this.state.publish, this.FlipPublish)} Publish to facebook
                        <MessageForm SendMessage={this.SendMessage.bind(this)} />
                    </div>
                    <br />
                    <div className="box-static" style={{ display: "block" }}>
                        <h5>
                            Output
                        </h5>
                        <br />
                        <span>
                            {this.state.output}
                        </span>
                    </div>
                </Container>
                <div className="side-bar gradient-left" />
                <div className="side-bar gradient-right" />
            </div>
        );
    }
}

class MessageForm extends Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};
        this.OnChange = this.OnChange.bind(this);
        this.OnSubmit = this.OnSubmit.bind(this);
    }

    OnChange(event) {
        this.setState({value: event.target.value});
    }

    OnSubmit(event) {
        this.props.SendMessage(this.state.value, false);
        this.setState({value: ''});
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.OnSubmit}>
                <label>
                    <input type="text" value={this.state.value} onChange={this.OnChange} />
                </label>
                <input type="submit" value="Submit" />
            </form>
        );
    }
}



export default App;