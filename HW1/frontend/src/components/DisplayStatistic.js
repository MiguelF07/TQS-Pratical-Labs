import React from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

function DisplayStatistic({data}) {
    let hasData=false
    let tmp;
    if(data!=="No Data")
    {
        tmp = JSON.parse(data)
        hasData=true
    }
  return (
    <Row>
        <Col>
        <p>{hasData ? "": "No Data"}</p>
        {hasData && 
            <>
            <b>Continent: </b> {tmp.continent===null ? <i>Null</i> : tmp.continent} <br/>
            <b>Country: </b> {tmp.country===null ? <i>Null</i> : tmp.country} <br/>
            <b>Population: </b> {tmp.population===null ? <i>Null</i> : tmp.population} <br/>
            <br/>
            <b>New Cases: </b> {tmp.cases.newCases===null ? <i>Null</i> : tmp.cases.newCases} <br/>
            <b>Active Cases: </b> {tmp.cases.active===null ? <i>Null</i> : tmp.cases.active} <br/>
            <b>Critical Cases: </b> {tmp.cases.critical===null ? <i>Null</i> : tmp.cases.critical} <br/>
            <b>Recovered Cases: </b> {tmp.cases.recovered===null ? <i>Null</i> : tmp.cases.recovered} <br/>
            <b>Cases per 1M: </b> {tmp.cases.oneM_pop===null ? <i>Null</i> : tmp.cases.oneM_pop} <br/>
            <b>Total Cases: </b> {tmp.cases.total===null ? <i>Null</i> : tmp.cases.total} <br/>
            <br/>
            <b>New Deaths: </b> {tmp.deaths.newDeaths===null ? <i>Null</i> : tmp.deaths.newDeaths} <br/>
            <b>Deaths per 1M: </b> {tmp.deaths.oneM_pop===null ? <i>Null</i> : tmp.deaths.oneM_pop} <br/>
            <b>Total Deaths: </b> {tmp.deaths.total===null ? <i>Null</i> : tmp.deaths.total} <br/>
            <br/>
            <b>Tests per 1M: </b> {tmp.tests.oneM_pop===null ? <i>Null</i> : tmp.tests.oneM_pop} <br/>
            <b>Total Tests: </b> {tmp.tests.total===null ? <i>Null</i> : tmp.tests.total} <br/>
            <br/>
            <b>Day: </b> {tmp.day===null ? <i>Null</i> : tmp.day} <br/>
            <b>Time: </b> {tmp.time===null ? <i>Null</i> : tmp.time} <br/>
            </>
            

        }
        
        </Col>
        
    </Row>
  )
}

export default DisplayStatistic