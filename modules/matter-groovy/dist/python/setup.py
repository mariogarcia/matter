import os
import sys
from setuptools import setup, find_packages


KEYWORDS=[
    'jupyter',
    'java',
    'groovy',
    'sql',
]


CLASSIFIERS=[
    'Development Status :: 5 - Production/Stable',
    'Framework :: IPython',
    'Intended Audience :: Developers',
    'Intended Audience :: Science/Research',
    'Topic :: Multimedia :: Graphics',
    'Programming Language :: Python :: 3',
    'Programming Language :: Python :: 3.3',
    'Programming Language :: Python :: 3.4',
    'Programming Language :: Python :: 3.5',
    'Programming Language :: Python :: 3.6',
    'Programming Language :: Groovy :: 3',
    'Programming Language :: Groovy :: 3.0.9',
]


PACKAGE_DATA={
    'beakerx_groovy': [
        'kernel/kernel.json',
        'kernel/lib/*',
        'kernel/ext/*'
    ]
}


ENTRY_POINTS={
    'console_scripts': [
        'beakerx_kernel_groovy = beakerx_groovy:run'
    ]
}


here = os.path.abspath(os.path.dirname(sys.argv[0]))
root = os.path.abspath(os.path.join(here, os.pardir))
kernel_path = os.path.join(root, './')


def get_version():
    path = os.path.join('beakerx_groovy', '_version.py')
    version = {}
    with open(os.path.join(here, path)) as f:
        exec (f.read(), {}, version)
    return version['__version__']


setup_args = dict(
    name='beakerx_kernel_groovy',
    description='BeakerX: Beaker Extensions for Jupyter Notebook',
    long_description='BeakerX: Beaker Extensions for Jupyter Notebook',
    version=get_version(),
    author='Two Sigma Open Source, LLC',
    author_email='beakerx-feedback@twosigma.com',
    url='http://beakerx.com',
    keywords=KEYWORDS,
    classifiers=CLASSIFIERS,
    entry_points=ENTRY_POINTS,
    package_data=PACKAGE_DATA,
    python_requires='>=3',
    zip_safe=False,
    include_package_data=True,
    packages=find_packages()
)

if __name__ == '__main__':
    setup(**setup_args)
