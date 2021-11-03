import originalData from './data.json';

export const CARD_TYPES = {
    DEFAULT: 'default',
    LOGO: 'logo',
    OVERLAY_TEXT: 'overlay-text',
    PROGRESS: 'progress',
    MIXED: 'mixed',
    VIDEO: 'video',
};

function shuffleArray(array, limit) {
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    return array.slice(0, limit);
}

function createDefaultDataArray() {
    return originalData.map((row) => {
        return {
            id: row.id,
            cardImageUrl: `https://image.tmdb.org/t/p/w500${row.backdrop_path}`,
            title: row.title,
            description: row.overview,
        };
    });
}

function createLogoCards(data) {
    const positions = ['center', 'right'];

    return data.map((row) => {
        return {
            ...row,
            overlayImageUrl: 'https://i.ibb.co/1GM0Sz3/rs-logo-white.png',
            overlayPosition: positions[Math.random() >= 0.5 ? 1 : 0],
        };
    });
}

function createOverlayTextCards(data) {
    return data.map((row) => {
        return {
            ...row,
            overlayText: row.title,
        };
    });
}

function createProgressCards(data) {
    return data.map((row) => {
        const randColor = '#' + ((Math.random() * 0xffffff) << 0).toString(16).padStart(6, '0');
        const randColorBar = '#' + ((Math.random() * 0xffffff) << 0).toString(16).padStart(6, '0');
        return {
            ...row,
            progress: Math.floor(Math.random() * 100) + 1,
            liveBadgeColor: randColor,
            liveProgressBarColor: Math.random() >= 0.8 ? randColorBar : randColor,
            displayLiveBadge: Math.random() < 0.5,
        };
    });
}

function createMixedCards(data) {
    const positions = ['center', 'right'];

    return data.map((row) => {
        const randColor = '#' + ((Math.random() * 0xffffff) << 0).toString(16).padStart(6, '0');
        const randColorBar = '#' + ((Math.random() * 0xffffff) << 0).toString(16).padStart(6, '0');

        return {
            ...row,
            ...(Math.random() >= 0.5 && {
                overlayImageUrl: 'https://i.ibb.co/1GM0Sz3/rs-logo-white.png',
                overlayPosition: positions[Math.random() >= 0.5 ? 1 : 0],
            }),
            ...(Math.random() >= 0.5 && {
                progress: Math.floor(Math.random() * 100) + 1,
                liveProgressBarColor: Math.random() >= 0.8 ? randColorBar : randColor,
                liveBadgeColor: randColor,
                displayLiveBadge: Math.random() < 0.5,
            }),
            ...(Math.random() >= 0.5 && { overlayText: row.title }),
        };
    });
}

const defaultCards = createDefaultDataArray();

export function generateData(type, limit = 20) {
    switch (type) {
        case CARD_TYPES.LOGO:
            return shuffleArray(createLogoCards(defaultCards), limit);
        case CARD_TYPES.OVERLAY_TEXT:
            return shuffleArray(createOverlayTextCards(defaultCards), limit);
        case CARD_TYPES.PROGRESS:
            return shuffleArray(createProgressCards(defaultCards), limit);
        case CARD_TYPES.MIXED:
            return shuffleArray(createMixedCards(defaultCards), limit);
        default:
            return shuffleArray(defaultCards, limit);
    }
}
