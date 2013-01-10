# Example by Fraser Tweedale: http://www.gitorious.org/frasertweedale/talks
# Using a Maybe monad for division, where there may be a failure due to division by zero

class Maybe(object):
    @classmethod
    def ret(cls, x): return Just(x)

class Nothing(Maybe):
    def __init__(self): pass
    def __rshift__(self, f): return self
    def __repr__(self): return 'Nothing()'

class Just(Maybe):
    def __init__(self, x): self._x = x
    def __rshift__(self, f):
        return f(self._x)
    def __repr__(self): return 'Just({!r})'.format(self._x)

def mdiv(n, d):
    return Nothing() if not d else Just(n / d)

print Just(10) >> (lambda x: Just(2) >> (lambda y: mdiv(x, y)))
print Just(10) >> (lambda x: Just(0) >> (lambda y: mdiv(x, y)))

def divby(d):
    return lambda n: mdiv(n, d)

print Just(10) >> divby(2)
print Just(10) >> divby(0)
print Just(10) >> divby(0) >> divby(2)
print Just(16) >> divby(2) >> divby(2)
