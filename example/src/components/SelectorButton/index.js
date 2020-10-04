import React, {useRef} from 'react';
import {StyleSheet, Text, TouchableOpacity} from 'react-native';

const styles = StyleSheet.create({
  button: {
    flex: 1,
    height: 70,
    width: 300,
    borderWidth: 1,
    borderColor: 'white',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 10,
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
  },
  text: {
    color: 'grey',
  },
});

export default function SelectorButton({title, onPress, hasTVPreferredFocus}) {
  const ref = useRef(null);
  return (
    <TouchableOpacity
      ref={ref}
      onPress={onPress}
      activeOpacity={0.5}
      title={title}
      onFocus={() => ref.current.setNativeProps({style: {opacity: 0.2}})}
      onBlur={() => ref.current.setNativeProps({style: {opacity: 1}})}
      style={styles.button}
      hasTVPreferredFocus={hasTVPreferredFocus}>
      <Text style={styles.text}>{title}</Text>
    </TouchableOpacity>
  );
}
