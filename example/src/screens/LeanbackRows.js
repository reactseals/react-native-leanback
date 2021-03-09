import React, { useEffect, useRef } from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';
import { Row } from 'react-native-leanback';
// import ScrollView from '@reactseals/react-native-snap-scrollview';

import data from '../data.json';

const LeanbackRows = () => {
    const ref = useRef(null);
    const swRef = useRef(null);

    useEffect(() => {
        ref.current.requestFocus();
        swRef.current.scrollTo({ y: 0 });
    }, []);

    return (
        <ScrollView
            ref={swRef}
            style={{ backgroundColor: '#1B1B1B' }}
            contentContainerStyle={{ marginTop: 30 }}
        >
            <View setSnapPoint>
                <Text style={styles.rowTitle}>Action</Text>
                <Row
                    ref={ref}
                    data={data}
                    style={{ width: '100%', height: 300 }}
                    attributes={{
                        width: 313,
                        height: 173,
                    }}
                />
            </View>
            <View setS napPoint>
                <Text style={styles.rowTitle}>Science Fiction</Text>
                <Row
                    data={data}
                    attributes={{
                        width: 313,
                        height: 173,
                        hasImageOnly: true,
                    }}
                    style={{ width: '100%', height: 270 }}
                />
            </View>
            <View setSnapPoint>
                <Text style={styles.rowTitle}>Comedy</Text>
                <Row
                    data={[...data, ...data, ...data]}
                    attributes={{
                        width: 213,
                        height: 173,
                        borderRadius: 36,
                    }}
                    style={{ width: '100%', height: 330 }}
                />
            </View>
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    rowTitle: {
        color: 'white',
        fontSize: 20,
        marginLeft: 30,
    },
});

export default LeanbackRows;
