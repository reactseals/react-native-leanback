import React from 'react';
import { ScrollView, TouchableOpacity } from 'react-native';
import { Grid } from 'react-native-leanback';

import { generateData, CARD_TYPES } from '../dataGenerator';

const LeanbackGrid = () => (
    <ScrollView style={{ backgroundColor: '#1B1B1B' }}>
        <TouchableOpacity style={{ width: 200, height: 200, borderColor: 'red', borderWidth: 1 }} />
        <Grid
            ref={(ref) => {
                ref.requestFocus();
            }}
            data={generateData(CARD_TYPES.DEFAULT, 300)}
            style={{ width: '100%', height: 500 }}
            attributes={{
                width: 300,
                height: 173,
            }}
            numOfCols={4}
            onFocus={(item) => console.log(item)}
        />
    </ScrollView>
);

export default LeanbackGrid;
