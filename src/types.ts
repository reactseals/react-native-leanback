import type { ViewStyle } from 'react-native';
import type {
  CardShape,
  CardAlingnment,
  ImageTransformationMode,
} from './constants';

export type CardData = {
  id: number;
  cardImageUrl: string;
  videoUrl?: string;
  title?: string;
  description?: string;
  liveBadgeColor?: string;
  liveProgressBarColor?: string;
  overlayImageUrl?: string;
  overlayText?: string;
  overlayPosition?: string;
  progress?: number;
  backgroundColor?: string;
  programStartTimestamp?: number;
  programEndTimestamp?: number;
  viewId?: number;
};

export type CardAttributes = {
  width?: number;
  height?: number;
  cardShape?: CardShape;
  focusedCardAlignment?: CardAlingnment;
  numberOfRows?: number;
  borderRadius?: number;
  imageTransformationMode?: ImageTransformationMode;
  hasImageOnly?: boolean;
};

export type GridProperties = {
  key?: number | string;
  attributes: CardAttributes;
  forbiddenFocusDirections?: string[];
  showOnlyFocusedInfo?: boolean;
  nextFocusUpId?: number;
  nextFocusDownId?: number;
  nextFocusLeftId?: number;
  nextFocusRightId?: number;
  data: CardData[];
  numOfCols?: number;
  style?: ViewStyle;
  onFocus: OnFocusFunction;
  onPress: OnPressFunction;
  onDataIdsReady: OnDataIdsReadyFunction;
};

export type RowProperties = {
  key?: number | string;
  attributes: CardAttributes;
  forbiddenFocusDirections?: string[];
  nextFocusUpId?: number;
  nextFocusDownId?: number;
  nextFocusLeftId?: number;
  nextFocusRightId?: number;
  data: CardData[];
  style?: ViewStyle;
  onFocus: OnFocusFunction;
  onPress: OnPressFunction;
  onDataIdsReady: OnDataIdsReadyFunction;
};

export type OnFocusFunction = (data: CardData[]) => CardData[];

export type OnPressFunction = (data: CardData[]) => CardData[];

export type OnDataIdsReadyFunction = (data: CardData[]) => CardData[];
