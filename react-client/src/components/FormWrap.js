import React, {Component} from 'react';

class FormWrap extends Component{
  render(){
    return(
      <div class="form-warp">
        <div class="form-group">
          <label>{this.props.id}</label>
          <input type="text" id="id" />
        </div>
        <div class="form-group">
          <label>{this.props.pw}</label>
          <input type="password" id="password" />
        </div>
      </div>
    );
  }
}

export default FormWrap;