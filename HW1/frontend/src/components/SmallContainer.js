import React from 'react'
import {useState,useEffect} from 'react'
import DisplayStatistic from './DisplayStatistic'
import Row from 'react-bootstrap/Row'
import DisplayHistory from './DisplayHistory'


function SmallContainer({data,hasHistory,historyData,days}) {
  
  return (
    <div className='small_container'>
    <Row>
    {hasHistory ? <h4>COVID-19 History</h4> : <h4>COVID-19 Most Recent Statistics</h4>}
    </Row>
    <Row>
    {hasHistory ? <DisplayHistory historyData={historyData} days={days}/> : <DisplayStatistic data={data}/>}
    </Row>
    </div>
  )
}

export default SmallContainer