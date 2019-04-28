#!/usr/bin/env python
# coding: utf-8


import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split


def main():
    data = pd.read_csv(
        'tweets.csv', encoding='ISO-8859-1', error_bad_lines=False,
        header=None, usecols=[0, 5]
    )
    data[0] = np.where(data[0] == 4, 1, 0)
    data = data.drop_duplicates(subset=[data.columns[1]])
    train, test = train_test_split(data, test_size=0.9, random_state=42)
    train.to_csv('train.csv', index=False, header=False)
    test.to_csv('test.txt', index=False, header=False, columns=[5])


if __name__ == '__main__':
    main()
