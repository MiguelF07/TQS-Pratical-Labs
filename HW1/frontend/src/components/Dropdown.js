import React from 'react'

function Dropdown({label,options,value,onChange}) {
  return (
    <div>
    <label>
        {label}
        <br></br>
        <select value={value} onChange={onChange}>
        {options.map((option,index) => (
          <option key={index}>{option}</option>
        ))}
        </select>
    </label>
    <p>Country Selected: {value}</p>
    </div>
  )
}

export default Dropdown