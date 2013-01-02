class Dog(object):
    def __setattr__(self, *args):
        raise TypeError("can't modify immutable instance")
    __delattr__ = __setattr__
    def __init__(self, name):
        super(Dog, self).__setattr__('name', name)

billy = Dog('Billy')
billy.name = 'Buster'