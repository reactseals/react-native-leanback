import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';

import LeanbackRows from './screens/LeanbackRows';
import LeanbackGrid from './screens/LeanbackGrid';
import Selector from './screens/Selector';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{headerShown: false}}>
        <Stack.Screen name="Selector" component={Selector} />
        <Stack.Screen name="LeanbackRows" component={LeanbackRows} />
        <Stack.Screen name="LeanbackGrid" component={LeanbackGrid} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
