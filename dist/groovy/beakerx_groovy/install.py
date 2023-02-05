# Copyright 2017 TWO SIGMA OPEN SOURCE, LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

'''Installs BeakerX into a Jupyter and Python environment.'''

import json
import os
import fnmatch
import pkg_resources
import subprocess
import tempfile

from string import Template

BEAKERX_GROOVY_PKG='beakerx_groovy'
KERNEL_DIR='kernel'

def _kernel_name():
    return "groovy"


def _classpath():
    return pkg_resources.resource_filename(BEAKERX_GROOVY_PKG, os.path.join(KERNEL_DIR, 'lib', '*'))


def _install_kernels(args):
    with tempfile.TemporaryDirectory() as tmpdir:
        kernel_dir = os.path.join(tmpdir, _kernel_name())
        os.mkdir(kernel_dir)

        # MOVE LOGOS TO FINAL DESTINATION
        _move_logos(
            pkg_resources.resource_filename(BEAKERX_GROOVY_PKG, KERNEL_DIR),
            kernel_dir
        )

        # CREATE kernel.json WITH FINAL DESTINATION
        with open(os.path.join(kernel_dir, 'kernel.json'), 'w') as f:
            classpath = json.dumps(os.pathsep.join([_classpath()]))
            template = pkg_resources.resource_string(BEAKERX_GROOVY_PKG, os.path.join(KERNEL_DIR, 'kernel.json'))
            contents = Template(template.decode()).substitute(PATH=classpath)
            f.write(contents)

        # INSTALL KERNEL
        install_cmd = [
            'jupyter', 'kernelspec', 'install', '--sys-prefix', '--replace', '--name', _kernel_name(), kernel_dir
        ]

        subprocess.check_call(install_cmd)


def _move_logos(from_dir, to_dir):
    for file in pkg_resources.resource_listdir(BEAKERX_GROOVY_PKG, KERNEL_DIR):
        if fnmatch.fnmatch(file, '*.png'):
            os.rename(os.path.join(from_dir, file), os.path.join(to_dir, file))


def _uninstall_kernels():
    uninstall_cmd = [
        'jupyter', 'kernelspec', 'remove', _kernel_name(), '-y', '-f'
    ]
    try:
        subprocess.check_call(uninstall_cmd)
    except subprocess.CalledProcessError:
        pass  # uninstall_cmd prints the appropriate message


def install(args):
    _install_kernels(args)


def uninstall(args):
    _uninstall_kernels()


if __name__ == "__main__":
    install()
