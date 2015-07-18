import sys
from collections import Counter
from numpy import random

points = eval(sys.stdin.read())

def linreg(points):
    N = sum(points.values())
    X, Y, XX, XY = 0, 0, 0, 0
    for p, n in points.items():
        x, y = p
        X += x * n
        Y += y * n
        XX += x * x * n
        XY += x * y * n
    mu = (XY - X * Y / N) / (XX - X * X / N)
    theta = (Y - mu * X) / N
    return mu, theta

mus, thetas = [], []

N = sum(points.values())
items = list(points.items())
prob = [p / N for _, p in items]
items = [p for p, _ in items]
for _ in range(1000):
    bootstrap = Counter()
    total = 0
    for p, n in zip(items, random.multinomial(N, prob)):
        bootstrap[p] += n
    mu, theta = linreg(bootstrap)
    mus.append(mu)
    thetas.append(theta)

mu, theta = linreg(points)

print('theta: {} [{}, {}]'.format(theta, min(thetas), max(thetas)))
print('mu:    {} [{}, {}]'.format(mu, min(mus), max(mus)))