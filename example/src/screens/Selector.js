import * as React from 'react';
import {ScrollView, View} from 'react-native';

import SelectorButton from '../components/SelectorButton';

export default function HomeScreen({navigation}) {
  return (
    <ScrollView>
      <View
        style={{
          flex: 1,
          alignItems: 'center',
          justifyContent: 'center',
          marginTop: 100,
        }}>
        <SelectorButton
          title="Leanback Row"
          onPress={() => navigation.navigate('LeanbackRows')}
          hasTVPreferredFocus
        />
        <SelectorButton
          title="Leanback Grid"
          onPress={() => navigation.navigate('LeanbackGrid')}
        />
      </View>
    </ScrollView>
  );
}
