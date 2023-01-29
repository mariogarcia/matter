#!/usr/bin/env bash

set -e
set -x

conda env create -n beakerx --file configuration.yml

. /opt/conda/etc/profile.d/conda.sh

conda activate beakerx
conda install conda-build
conda build .
conda install beakerx_kernel_groovy