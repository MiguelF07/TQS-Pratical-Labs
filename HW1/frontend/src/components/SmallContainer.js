import React from 'react'
import {useState,useEffect} from 'react'
import DisplayStatistic from './DisplayStatistic'
import Row from 'react-bootstrap/Row'


function SmallContainer({data}) {
  
  return (
    <div className='small_container'>
    <Row>
    <h4>COVID-19 Statistics</h4>
    </Row>
    <Row>
    <DisplayStatistic data={data}/>
    </Row>
    </div>
  )
}

export default SmallContainer