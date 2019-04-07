#!/usr/bin/env python
# coding: utf-8


import pandas as pd
import numpy as np


def main():
    data = pd.read_csv(
        'tweets.csv', encoding='ISO-8859-1', error_bad_lines=False,
        header=None, usecols=[0, 5]
    )
    data[0] = np.where(data[0] == 4, 1, 0)
    data = data.drop_duplicates(subset=[data.columns[1]])
    d = pd.concat([data, data, data, data, data])
    d.to_csv('corpus.csv', index=False, header=False)


if __name__ == '__main__':
    main()
