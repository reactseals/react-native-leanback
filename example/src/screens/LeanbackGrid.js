import React from 'react';
import { ScrollView } from 'react-native';
import { Grid } from 'react-native-leanback';

import { generateData, CARD_TYPES } from '../dataGenerator';

const LeanbackGrid = () => (
    <ScrollView style={{ backgroundColor: '#1B1B1B' }}>
        <Grid
            data={generateData(CARD_TYPES.DEFAULT, 300)}
            style={{ width: '100%', height: 500 }}
            attributes={{
                width: 300,
                height: 173,
            }}
            numOfCols={4}
        />
    </ScrollView>
);

export default LeanbackGrid;
