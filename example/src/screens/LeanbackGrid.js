import React from 'react';
import {ScrollView} from 'react-native';
import {Grid} from 'react-native-leanback';

import data from '../data.json';

const mergedData = [...data, ...data, ...data, ...data, ...data];

const LeanbackGrid = () => (
  <ScrollView style={{backgroundColor: '#1B1B1B'}}>
    <Grid
      data={mergedData}
      style={{width: '100%', height: 500}}
      attributes={{
        width: 300,
        height: 173,
      }}
      numOfCols={4}
    />
  </ScrollView>
);

export default LeanbackGrid;
